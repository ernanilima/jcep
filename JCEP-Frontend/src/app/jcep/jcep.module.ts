import { NgModule } from '@angular/core';

import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { DropdownModule } from 'primeng/dropdown';
import { JCEPRoutingModule } from './jcep-routing.module';
import { JCEPComponent } from './page/jcep.component';

@NgModule({
  declarations: [JCEPComponent],
  imports: [CommonModule, JCEPRoutingModule, DropdownModule, FormsModule],
})
export class JCEPModule {}
