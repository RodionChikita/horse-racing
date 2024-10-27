import { Component, OnInit } from '@angular/core';
import { HorseService } from '../horse.service';
import { OwnerDto } from "../../owner/owner.models";
import { CreateOrUpdateHorseDtoRq, HorseDto } from "../horse.models";
import { OwnerService } from "../../owner/owner.service";
import { Observable, of } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';

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
    this.loadOwners().subscribe(() => {
      this.loadHorses();
    });
  }

  loadHorses(): void {
    this.horseService.findAll().subscribe(
      (data: HorseDto[]) => this.horses = data,
      (error: any) => console.error('Error loading horses', error)
    );
  }

  loadOwners(): Observable<OwnerDto[]> {
    return this.ownerService.findAll().pipe(
      tap((owners) => this.owners = owners),
      catchError((error) => {
        console.error('Error loading owners', error);
        return of([]);
      })
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
      ownerId: event.data.ownerId, // Убедитесь, что формируется правильно
    };

    this.horseService.insert(newHorse).subscribe(
      (createdHorse: HorseDto) => {
        const owner = this.owners.find(o => o.id === newHorse.ownerId);
        this.horses.push({
          ...createdHorse,
          owner: owner ?? { id: newHorse.ownerId, name: 'Unknown', address: '', phoneNumber: '' }
        });
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
      ownerId: event.newData.ownerId || event.oldData.ownerId, // Убедитесь, что правильно определяется
    };

    this.horseService.update(updatedHorse).subscribe(
      () => {
        const updatedIndex = this.horses.findIndex(horse => horse.id === updatedHorse.id);
        if (updatedIndex > -1) {
          this.horses[updatedIndex].owner = this.owners.find(o => o.id === updatedHorse.ownerId) ?? this.horses[updatedIndex].owner;
        }
      },
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
