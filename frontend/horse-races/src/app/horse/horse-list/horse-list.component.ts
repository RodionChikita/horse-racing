// В файле horse-list.component.ts
import { Component, OnInit } from '@angular/core';
import { HorseService } from '../horse.service';
import { OwnerDto } from "../../owner/owner.models";
import { CreateOrUpdateHorseDtoRq, HorseDto } from "../horse.models";
import { OwnerService } from "../../owner/owner.service";

@Component({
    selector: 'app-horse-list',
    templateUrl: './horse-list.component.html',
    styleUrls: ['./horse-list.component.scss']
})
export class HorseListComponent {
  horses: HorseDto[] = [];  // Загрузите лошадей из сервиса
  owners: OwnerDto[] = [];  // Загрузите владельцев из сервиса

    constructor(private horseService: HorseService, private ownerService: OwnerService) {}

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

  getOwnerName(ownerId: number): string {
    const owner = this.owners.find(o => o.id === ownerId);
    return owner ? owner.name : 'Unknown';
  }

  addHorse(event: any) {
    // Используйте event.data для сохранения, идите через service
    const newHorse: CreateOrUpdateHorseDtoRq = {
      nickname: event.data.nickname,
      genderEnum: event.data.genderEnum,
      age: event.data.age,
      ownerId: event.data.ownerId, // Передаем ownerId
    };
    // Обработка сохранения newHorse через сервис
  }

  updateHorse(event: any) {
    const updatedHorse: CreateOrUpdateHorseDtoRq = {
      id: event.oldData.id,
      nickname: event.newData.nickname || event.oldData.nickname,
      genderEnum: event.newData.genderEnum || event.oldData.genderEnum,
      age: event.newData.age || event.oldData.age,
      ownerId: event.newData.ownerId || event.oldData.ownerId, // Передаем ownerId
    };


//
//     addHorse(e: any) {
//         const newHorse: CreateOrUpdateHorseDtoRq = e.data;
//         console.log('Inserting horse data:', newHorse);
//         e.promise = this.horseService.insert(newHorse).toPromise().then(
//             () => this.loadHorses(),
//             (error) => {
//                 console.error('Failed to add horse', error);
//                 e.cancel = true;
//             }
//         );
//     }
//
//     updateHorse(e: any) {
//         const updatedHorse: CreateOrUpdateHorseDtoRq = { ...e.oldData, ...e.newData };
//         e.promise = this.horseService.update(updatedHorse).toPromise().then(
//             () => this.loadHorses(),
//             (error) => {
//                 console.error('Failed to update horse', error);
//                 e.cancel = true;
//             }
//         );
//     }

    deleteHorse(e: any) {
        const horseId = e.data.id;
        e.promise = this.horseService.deleteById(horseId).toPromise().then(
            () => this.loadHorses(),
            (error) => {
                console.error('Failed to delete horse', error);
                e.cancel = true;
            }
        );
    }
}
