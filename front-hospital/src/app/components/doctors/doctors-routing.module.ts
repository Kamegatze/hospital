import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {DoctorsComponent} from "./doctors.component";
import {UpdateDataComponent} from "./update-data/update-data.component";
import {TableComponent} from "./table/table.component";
import {AdditionDataComponent} from "./addition-data/addition-data.component";
import {UnderTableComponent} from "./under-table/under-table.component";

const routes: Routes = [
  {path:"table", component:TableComponent},
  {path:"", redirectTo:"table", pathMatch:"full"},
  {path:"update-data/:id", component:UpdateDataComponent},
  {path:"addition-data", component:AdditionDataComponent},
  {path:"under-table/:id", component:UnderTableComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DoctorsRoutingModule { }
