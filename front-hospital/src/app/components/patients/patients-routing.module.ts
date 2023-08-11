import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {TableComponent} from "./table/table.component";
import {UnderTableComponent} from "./under-table/under-table.component";
import {AdditionDataComponent} from "./addition-data/addition-data.component";
import {UpdateDataComponent} from "./update-data/update-data.component";
import {AdditionReceptionComponent} from "./addition-reception/addition-reception.component";

const routes: Routes = [
  {path:"table", component:TableComponent},
  {path:"", redirectTo:"table", pathMatch:"full"},
  {path:"under-table/:id", component:UnderTableComponent},
  {path:"addition-data", component:AdditionDataComponent},
  {path:"update-data/:id", component:UpdateDataComponent},
  {path:"under-table/addition-reception/:id", component:AdditionReceptionComponent},

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PatientsRoutingModule { }
