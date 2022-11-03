import { Component, OnInit } from '@angular/core';
import { CountryDto } from '../shared/interface/country.dto';

@Component({
  selector: 'jcep',
  templateUrl: './jcep.component.html',
})
export class JCEPComponent implements OnInit {
  public selectedCountry: string;

  public countries: CountryDto[];

  ngOnInit(): void {
    this.countries = [
      { description: 'Australia', acronym: 'AU' },
      { description: 'Brasil', acronym: 'BR' },
      { description: 'China', acronym: 'CN' },
      { description: 'Egypt', acronym: 'EG' },
      { description: 'France', acronym: 'FR' },
      { description: 'Germany', acronym: 'DE' },
      { description: 'India', acronym: 'IN' },
      { description: 'Japan', acronym: 'JP' },
      { description: 'Spain', acronym: 'ES' },
      { description: 'United States', acronym: 'US' },
    ];
  }

  public changeCountries(value: string): void {
    console.log(value);
  }
}
