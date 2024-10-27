import { Component, OnInit } from '@angular/core';
import { HorseService } from '../horse.service';
import { OwnerService } from '../../owner/owner.service';
import { OwnerDto } from '../../owner/owner.models';
import { HorseDto, CreateOrUpdateHorseDtoRq } from '../horse.models';
import { Observable, of } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';

@Component({
  selector: 'app-horse-list',
  templateUrl: './horse-list.component.html',
})
export class  HorseListComponent {
  horses: HorseDto[] = [];
  owners: OwnerDto[] = [];

  constructor(private horseService: HorseService, private ownerService: OwnerService) {
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

  onRowInserting(e: any) {
    const newHorse: CreateOrUpdateHorseDtoRq = {
      nickname: e.data.nickname,
      genderEnum: e.data.genderEnum,
      age: e.data.age,
      ownerId: e.data.ownerId,
    };

    this.horseService.insert(newHorse).subscribe(() => {
      this.loadHorses();
    });
  }

  onRowUpdating(e: any) {
    const updatedHorse: CreateOrUpdateHorseDtoRq = {
      id: e.oldData.id,
      nickname: e.newData.nickname || e.oldData.nickname,
      genderEnum: e.newData.genderEnum || e.oldData.genderEnum,
      age: e.newData.age || e.oldData.age,
      ownerId: e.newData.ownerId || e.oldData.ownerId,
    };

    this.horseService.update(updatedHorse).subscribe(() => {
      this.loadHorses();
    });
  }

  onRowRemoving(e: any) {
    this.horseService.deleteById(e.data.id).subscribe(() => {
      this.loadHorses();
    });
  }
}