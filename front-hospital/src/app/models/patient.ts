import {Doctor} from "./doctor";

export interface Patient {
  id:number,
  firstName:string,
  lastName:string,
  patronymic:string,
  disease:string,
  age:number,
  doctorDTOS:Doctor[]
}
