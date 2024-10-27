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
    this.horseService.findAll().subscribe( // Вызов как функции
      (data: HorseDto[]) => this.horses = data,
      (error: any) => console.error('Error loading horses', error)
    );
  }

  loadOwners(): void {
    this.ownerService.findAll().subscribe( // Вызов как функции
      (data: OwnerDto[]) => this.owners = data,
      (error: any) => console.error('Error loading owners', error)
    );
  }

  getOwnerName(ownerId: number): string {
    const owner = this.owners.find(o => o.id === ownerId);
    return owner ? owner.name : 'Unknown';
  }

  addHorse(event: any): void {
    const newHorse: CreateOrUpdateHorseDtoRq = {
      nickname: event.data.nickname,
      genderEnum: event.data.genderEnum,
      age: event.data.age,
      ownerId: event.data.ownerId,
    };

    this.horseService.insert(newHorse).subscribe(
      (response: any) => {
        const owner = this.owners.find(o => o.id === newHorse.ownerId);
        const transformedHorse: HorseDto = {
          id: response.id,
          nickname: response.nickname,
          genderEnum: response.genderEnum,
          age: response.age,
          owner: owner ?? { id: newHorse.ownerId, name: 'Unknown', address: '', phoneNumber: '' }
        };
        this.horses.push(transformedHorse);
      },
      (error: any) => console.error('Error adding horse', error)
    );
  }

  updateHorse(event: any): void {
    const updatedHorse: CreateOrUpdateHorseDtoRq = {
      id: event.oldData.id,
      nickname: event.newData.nickname || event.oldData.nickname,
      genderEnum: event.newData.genderEnum || event.oldData.genderEnum,
      age: event.newData.age || event.oldData.age,
      ownerId: event.newData.ownerId || event.oldData.ownerId,
    };
    this.horseService.update(updatedHorse).subscribe( // Измените метод на update
      () => {},
      (error: any) => console.error('Error updating horse', error)
    );
  }

  deleteHorse(event: any): void {
    const horseId = event.data.id;
    event.promise = this.horseService.deleteById(horseId).toPromise().then(
      () => {
        this.horses = this.horses.filter(h => h.id !== horseId);
      },
      (error: any) => {
        console.error('Error deleting horse', error);
        event.cancel = true;
      }
    );
  }
}
