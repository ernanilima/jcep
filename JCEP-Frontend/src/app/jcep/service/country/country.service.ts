import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';

import { map, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Page, QueryBuilder } from '../../shared/params/page-params';
import { CountryDto } from '../../shared/interface/country.dto';

@Injectable()
export class CountryService {

  constructor(
    private _http: HttpClient
  ) {}

  /**
   * Buscar todos os paises
   * @param queryBuilder QueryBuilder
   * @returns Observable<Page<CountryDto>>
   */
  public getCountries(queryBuilder: QueryBuilder): Observable<Page<CountryDto>> {
    return this._http
      .get<CountryDto[]>(`${environment.urlBackend}/endereco/pais?${queryBuilder.buildQueryString()}`, {
        observe: 'response',
      }).pipe(
        map((response: HttpResponse<any>) => Page.fromResponse(response))
      );
  }
}
