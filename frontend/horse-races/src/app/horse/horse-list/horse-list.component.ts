import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HorseService } from '../horse.service';
import { OwnerService } from '../../owner/owner.service';
import { HorseDto, CreateOrUpdateHorseDtoRq, GenderEnum } from '../horse.models';
import { OwnerDto } from '../../owner/owner.models';

@Component({
  selector: 'app-horse-list',
  templateUrl: './horse-list.component.html',
  styleUrls: ['./horse-list.component.scss']
})
export class HorseListComponent implements OnInit {
  horseForm: FormGroup;
  horses: HorseDto[] = [];
  owners: OwnerDto[] = [];

  constructor(
    private fb: FormBuilder,
    private horseService: HorseService,
    private ownerService: OwnerService
  ) {
    this.horseForm = this.fb.group({
      nickname: ['', Validators.required],
      genderEnum: ['MALE', Validators.required],
      age: [0, Validators.required],
      ownerId: [null, Validators.required]
    });
  }

  ngOnInit() {
    this.loadHorses();
    this.loadOwners();
  }

  loadHorses() {
    this.horseService.findAll().subscribe((data) => {
      this.horses = data;
    });
  }

  loadOwners() {
    this.ownerService.findAll().subscribe((data) => {
      this.owners = data;
    });
  }

  addHorse(e: any) {
    const newHorse: CreateOrUpdateHorseDtoRq = this.horseForm.value;
    e.promise = this.horseService.insert(newHorse).toPromise().then(
      () => {
        this.loadHorses();
      },
      (error: any) => {
        console.error('Failed to add horse', error);
        e.cancel = true;
      }
    );
  }

  updateHorse(e: any) {
    const updatedHorse: CreateOrUpdateHorseDtoRq = { ...e.oldData, ...e.newData };
    e.promise = this.horseService.update(updatedHorse).toPromise().then(
      () => {
        this.loadHorses();
      },
      (error: any) => {
        console.error('Failed to update horse', error);
        e.cancel = true;
      }
    );
  }

  deleteHorse(e: any) {
    const horseId = e.data.id;
    e.promise = this.horseService.deleteById(horseId).toPromise().then(
      () => {
        this.loadHorses();
      },
      (error: any) => {
        console.error('Failed to delete horse', error);
        e.cancel = true;
      }
    );
  }

  saveHorse() {
    if (this.horseForm.valid) {
      const newHorse: CreateOrUpdateHorseDtoRq = this.horseForm.value;
      this.horseService.insert(newHorse).subscribe(
        () => {
          this.loadHorses();
        },
        (error) => {
          console.error('Failed to save horse', error);
        }
      );
    }
  }
}
