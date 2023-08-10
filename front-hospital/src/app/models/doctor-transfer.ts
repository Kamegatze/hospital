import {FormControl} from "@angular/forms";

export interface DoctorTransfer {
  id:FormControl<number | null>,
  firstName:FormControl<string>,
  lastName:FormControl<string>,
  patronymic:FormControl<string>,
  post:FormControl<string>,
  jobTimeBeginHour:FormControl<string>,
  jobTimeBeginMinute:FormControl<string>,
  jobTimeEndHour:FormControl<string>,
  jobTimeEndMinute:FormControl<string>
}
