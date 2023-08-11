import {Patient} from "./patient";

export interface Doctor {
  id:number,
  firstName:string,
  lastName:string,
  patronymic:string,
  post:string,
  jobTimeBegin:string,
  jobTimeEnd:string
  patientDTOS:Patient[]|null
}
