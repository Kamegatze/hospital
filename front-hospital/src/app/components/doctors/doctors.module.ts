import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DoctorsRoutingModule } from './doctors-routing.module';
import { UpdateDataComponent } from './update-data/update-data.component';
import { TableComponent } from './table/table.component';
import {ReactiveFormsModule} from "@angular/forms";
import { AdditionDataComponent } from './addition-data/addition-data.component';
import { UnderTableComponent } from './under-table/under-table.component';


@NgModule({
  declarations: [
    UpdateDataComponent,
    TableComponent,
    AdditionDataComponent,
    UnderTableComponent
  ],
  imports: [
    CommonModule,
    DoctorsRoutingModule,
    ReactiveFormsModule
  ]
})
export class DoctorsModule { }
