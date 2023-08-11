import {FormControl} from "@angular/forms";
import {Doctor} from "./doctor";

export interface PatientTransfer {
  id:FormControl<number | null>,
  firstName:FormControl<string>,
  lastName:FormControl<string>,
  patronymic:FormControl<string>,
  disease:FormControl<string>,
  age:FormControl<number>,
  doctorDTOS:FormControl<Doctor[]>
}
