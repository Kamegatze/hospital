import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PatientsRoutingModule } from './patients-routing.module';
import { PatientsComponent } from './patients.component';
import { TableComponent } from './table/table.component';
import { UnderTableComponent } from './under-table/under-table.component';


@NgModule({
  declarations: [
    PatientsComponent,
    TableComponent,
    UnderTableComponent
  ],
  imports: [
    CommonModule,
    PatientsRoutingModule
  ]
})
export class PatientsModule { }
