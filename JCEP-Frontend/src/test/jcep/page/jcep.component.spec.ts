import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JCEPComponent } from '../../../app/jcep/page/jcep.component';

describe('JCEPComponent', () => {
  let component: JCEPComponent;
  let fixture: ComponentFixture<JCEPComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [JCEPComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(JCEPComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
