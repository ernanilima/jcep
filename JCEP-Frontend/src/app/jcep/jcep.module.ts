import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { JCEPComponent } from './page/jcep.component';
import { JCEPRoutingModule } from './jcep-routing.module';

@NgModule({
  declarations: [JCEPComponent],
  imports: [CommonModule, JCEPRoutingModule],
})
export class JCEPModule {}
