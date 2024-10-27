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
    this.horseService.findAll().subscribe(data => {
      this.horses = data;
    });
  }

  loadOwners() {
    this.ownerService.findAll().subscribe(data => {
      this.owners = data;
    });
  }

  insertHorse(horse: CreateOrUpdateHorseDtoRq) {
    this.horseService.insert(horse).subscribe(() => {
      this.loadHorses();
    });
  }

  updateHorse(horse: CreateOrUpdateHorseDtoRq) {
    this.horseService.update(horse).subscribe(() => {
      this.loadHorses();
    });
  }

  deleteHorse(id: number) {
    this.horseService.deleteById(id).subscribe(() => {
      this.loadHorses();
    });
  }
 }