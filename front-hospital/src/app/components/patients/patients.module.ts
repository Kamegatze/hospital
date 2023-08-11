import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PatientsRoutingModule } from './patients-routing.module';
import { PatientsComponent } from './patients.component';
import { TableComponent } from './table/table.component';
import { UnderTableComponent } from './under-table/under-table.component';
import { AdditionDataComponent } from './addition-data/addition-data.component';
import {ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    PatientsComponent,
    TableComponent,
    UnderTableComponent,
    AdditionDataComponent
  ],
  imports: [
    CommonModule,
    PatientsRoutingModule,
    ReactiveFormsModule
  ]
})
export class PatientsModule { }
