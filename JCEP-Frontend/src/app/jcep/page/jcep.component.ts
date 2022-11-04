import { Component, OnDestroy, OnInit } from '@angular/core';

import { Subject, takeUntil } from 'rxjs';
import { CountryService } from '../service/country/country.service';
import { CountryDto } from '../shared/interface/country.dto';
import { Page, PageRequest } from '../shared/params/page-params';

@Component({
  selector: 'jcep',
  templateUrl: './jcep.component.html',
})
export class JCEPComponent implements OnInit, OnDestroy {
  private _destroy: Subject<boolean> = new Subject<boolean>();

  private page: PageRequest = PageRequest.of({ pageSize: 48 });

  public selectedCountry: string;
  public countries: CountryDto[];

  constructor(
    private _countryService: CountryService,
  ) {}

  public ngOnInit(): void {
    this._getCountries();
  }

  public ngOnDestroy(): void {
    this._destroy.next(true);
    this._destroy.unsubscribe();
  }

  private _getCountries(): void {
    this._countryService
      .getCountries(this.page)
      .pipe(takeUntil(this._destroy))
      .subscribe((dto: Page<CountryDto>) => {
        this.countries = dto.content;
      });
  }

  public filterCountries(value: string): void {
    console.log(value);
  }

  public changeCountries(value: string): void {
    console.log(value);
  }
}
