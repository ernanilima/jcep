import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { of } from 'rxjs';
import { JCEPModule } from 'src/app/jcep/jcep.module';
import { JCEPComponent } from 'src/app/jcep/page/jcep.component';
import { CountryService } from 'src/app/jcep/service/country/country.service';
import SpyObj = jasmine.SpyObj;

describe(`${JCEPComponent.name}`, () => {

  let fixture: ComponentFixture<JCEPComponent>;
  let countryServiceMock: SpyObj<CountryService>;

  beforeEach(waitForAsync(() => {
    countryServiceMock = jasmine.createSpyObj('countryServiceMock', ['getCountries']);
    countryServiceMock.getCountries.and.callFake(() => of());

    TestBed.configureTestingModule({
      imports: [
        JCEPModule
      ],
      providers: [
        { provide: CountryService, useValue: countryServiceMock },
      ],
    }).compileComponents().then(() => {
      fixture = TestBed.createComponent(JCEPComponent);
      fixture.detectChanges();
    });
  }));

  it(`renderizar ${JCEPComponent.name}`, () => {
    expect(fixture).toBeTruthy();
  });
});
