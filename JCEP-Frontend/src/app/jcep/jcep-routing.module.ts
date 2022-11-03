import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { JCEPComponent } from './page/jcep.component';

const routes: Routes = [
  {
    path: '',
    component: JCEPComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class JCEPRoutingModule {}
