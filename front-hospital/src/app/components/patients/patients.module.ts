import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PatientsRoutingModule } from './patients-routing.module';
import { PatientsComponent } from './patients.component';
import { TableComponent } from './table/table.component';
import { UnderTableComponent } from './under-table/under-table.component';
import { AdditionDataComponent } from './addition-data/addition-data.component';
import {ReactiveFormsModule} from "@angular/forms";
import { UpdateDataComponent } from './update-data/update-data.component';
import { AddtionReceptionComponent } from './addtion-reception/addtion-reception.component';


@NgModule({
  declarations: [
    PatientsComponent,
    TableComponent,
    UnderTableComponent,
    AdditionDataComponent,
    UpdateDataComponent,
    AddtionReceptionComponent
  ],
  imports: [
    CommonModule,
    PatientsRoutingModule,
    ReactiveFormsModule
  ]
})
export class PatientsModule { }
