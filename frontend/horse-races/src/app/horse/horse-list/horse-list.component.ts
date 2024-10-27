// В файле horse-list.component.ts
import { Component, OnInit } from '@angular/core';
import { HorseService } from '../horse.service';
import { OwnerDto } from "../../owner/owner.models";
import { CreateOrUpdateHorseDtoRq, HorseDto } from "../horse.models";
import { OwnerService } from "../../owner/owner.service";

@Component({
  selector: 'app-horse-list',
  templateUrl: './horse-list.component.html',
})
export class HorseListComponent implements OnInit {
  horses: HorseDto[] = [];
  owners: OwnerDto[] = [];

  constructor(
    private horseService: HorseService,
    private ownerService: OwnerService
  ) {}

  ngOnInit(): void {
    this.loadHorses();
    this.loadOwners();
  }

  loadHorses(): void {
    this.horseService.getHorses().subscribe(
      (data) => this.horses = data,
      (error) => console.error('Error loading horses', error)
    );
  }

  loadOwners(): void {
    this.ownerService.getOwners().subscribe(
      (data) => this.owners = data,
      (error) => console.error('Error loading owners', error)
    );
  }

  getOwnerName(ownerId: number): string {
    const owner = this.owners.find(o => o.id === ownerId);
    return owner ? owner.name : 'Unknown';
  }

  addHorse(event: any) {
    const newHorse: CreateOrUpdateHorseDtoRq = {
      nickname: event.data.nickname,
      genderEnum: event.data.genderEnum,
      age: event.data.age,
      ownerId: event.data.ownerId,
    };
    this.horseService.createHorse(newHorse).subscribe(
      (horse) => this.horses.push(horse),
      (error) => console.error('Error adding horse', error)
    );
  }

  updateHorse(event: any) {
    const updatedHorse: CreateOrUpdateHorseDtoRq = {
      id: event.oldData.id,
      nickname: event.newData.nickname || event.oldData.nickname,
      genderEnum: event.newData.genderEnum || event.oldData.genderEnum,
      age: event.newData.age || event.oldData.age,
      ownerId: event.newData.ownerId || event.oldData.ownerId,
    };
    this.horseService.updateHorse(updatedHorse).subscribe(
      () => {},
      (error) => console.error('Error updating horse', error)
    );
  }

  deleteHorse(event: any) {
    const horseId = event.data.id;
    event.promise = this.horseService.deleteById(horseId).toPromise().then(
      () => {
        // Удаление прошло успешно, обновите список лошадей
        this.horses = this.horses.filter(h => h.id !== horseId);
      },
      (error) => {
        console.error('Error deleting horse', error);
        event.cancel = true;
      }
    );
  }
}

