import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {DoctorsComponent} from "./components/doctors/doctors.component";

const routes: Routes = [
  {
    path:"doctors",
    loadChildren: () => import('./components/doctors/doctors.module')
      .then((module) => module.DoctorsModule),
  },
  {path:"", redirectTo:"doctors", pathMatch:"full"},
  {
    path:"patients",
    loadChildren: () => import('./components/patients/patients.module')
      .then((module) => module.PatientsModule),
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
