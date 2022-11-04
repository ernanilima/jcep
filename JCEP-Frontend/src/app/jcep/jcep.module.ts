import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { JCEPRoutingModule } from './jcep-routing.module';
import { JCEPComponent } from './page/jcep.component';
import { CountryService } from './service/country/country.service';
import { DropdownModule } from 'primeng/dropdown';

@NgModule({
  declarations: [
    JCEPComponent,
  ],
  imports: [
    CommonModule,
    JCEPRoutingModule,
    DropdownModule,
    FormsModule,
  ],
  providers: [
    CountryService,
  ],
})
export class JCEPModule {}
