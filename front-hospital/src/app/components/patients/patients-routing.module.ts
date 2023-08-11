import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {TableComponent} from "./table/table.component";
import {UnderTableComponent} from "./under-table/under-table.component";

const routes: Routes = [
  {path:"table", component:TableComponent},
  {path:"", redirectTo:"table", pathMatch:"full"},
  {path:"under-table/:id", component:UnderTableComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PatientsRoutingModule { }
