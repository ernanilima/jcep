import { Component, OnInit } from '@angular/core';

import { CountryService } from '../service/country/country.service';
import { CountryDto } from '../shared/interface/country.dto';
import { Page } from '../shared/params/page-params';

@Component({
  selector: 'jcep',
  templateUrl: './jcep.component.html',
})
export class JCEPComponent implements OnInit {

  public selectedCountry: string;
  public countries: CountryDto[];

  constructor(
    private _countryService: CountryService,
  ) {}

  public ngOnInit(): void {
    this._getCountries();
  }

  private _getCountries(): void {
    this._countryService
      .getCountries()
      .subscribe((dto: Page<CountryDto>) => {
        this.countries = dto.content;
      });
  }

  public changeCountries(value: string): void {
    console.log(value);
  }
}
