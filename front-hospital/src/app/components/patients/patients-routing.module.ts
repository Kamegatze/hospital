import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {TableComponent} from "./table/table.component";
import {UnderTableComponent} from "./under-table/under-table.component";
import {AdditionDataComponent} from "./addition-data/addition-data.component";

const routes: Routes = [
  {path:"table", component:TableComponent},
  {path:"", redirectTo:"table", pathMatch:"full"},
  {path:"under-table/:id", component:UnderTableComponent},
  {path:"addition-data", component:AdditionDataComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PatientsRoutingModule { }
