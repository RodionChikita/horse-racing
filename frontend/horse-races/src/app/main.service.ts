import {inject, Injectable} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Injectable({
  providedIn: 'root'
})
export class MainService {
  private _formBuilder: FormBuilder = inject(FormBuilder);

  public get form(): FormGroup {
    return this._formBuilder.group({
      animalName: [null, Validators.required],
      vaccinated: [false, Validators.required],
      phone: [null, Validators.required],
      price: [null, Validators.required],
      dataTime: [null, Validators.required]
    });
  }
}
