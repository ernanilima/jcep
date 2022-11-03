import { TestBed } from '@angular/core/testing';

import { CountryService } from 'src/app/jcep/service/country/country.service';
import { CountryDto } from 'src/app/jcep/shared/interface/country.dto';
import { Page } from 'src/app/jcep/shared/params/page-params';
import { of } from 'rxjs';
import SpyObj = jasmine.SpyObj;

export const countries: Page<CountryDto> = new Page(
  [
    {
      id: '3c3575ae-c28c-40fd-9a87-ee43d49d44c3',
      value: 'BRASIL',
      description: 'Brasil',
      acronym: 'BR',
      code: '1058'
    }, {
      id: 'b7fa4a84-aaa5-48ef-a617-897c63dd6d47',
      value: 'ARGENTINA',
      description: 'Argetina',
      acronym: 'AR',
      code: '639'
    }
  ], 2);

describe(`${CountryService.name}`, () => {

  let countryService: SpyObj<CountryService>;

  beforeEach(() => {
    countryService = jasmine.createSpyObj('countryService', ['getCountries']);

    TestBed.configureTestingModule({
      providers: [
        { provide: CountryService, useValue: countryService },
      ]
    });
  });

  it(`renderizar ${CountryService.name}`, () => {
    expect(countryService).toBeTruthy();
  });

  it(`deve retornar os paises - ${CountryService.name}`, () => {
    countryService.getCountries.and.callFake(() => of(countries));

    countryService.getCountries().subscribe((result: Page<CountryDto>) => {
      expect(result).toHaveSize(2);

      expect(result.content[0].id).toContain(countries.content[0].id);
      expect(result.content[0].value).toContain('BRASIL');
      expect(result.content[0].description).toContain('Brasil');
      expect(result.content[0].acronym).toContain('BR');
      expect(result.content[0].code).toContain('1058');

      expect(result.content[1].id).toContain(countries.content[1].id);
      expect(result.content[1].value).toContain('ARGENTINA');
      expect(result.content[1].description).toContain('Argetina');
      expect(result.content[1].acronym).toContain('AR');
      expect(result.content[1].code).toContain('639');
    });
  });
});
