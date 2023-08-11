import {FormControl} from "@angular/forms";
import {Patient} from "./patient";

export interface DoctorTransfer {
  id:FormControl<number | null>,
  firstName:FormControl<string>,
  lastName:FormControl<string>,
  patronymic:FormControl<string>,
  post:FormControl<string>,
  jobTimeBeginHour:FormControl<string>,
  jobTimeBeginMinute:FormControl<string>,
  jobTimeEndHour:FormControl<string>,
  jobTimeEndMinute:FormControl<string>,
  patientDTOS:FormControl<Patient[]>
}
