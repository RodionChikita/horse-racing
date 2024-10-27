import { Component, OnInit } from '@angular/core';
import { HorseService } from '../horse.service';
import {OwnerDto} from "../../owner/owner.models";
import {CreateOrUpdateHorseDtoRq, HorseDto, GenderEnum} from "../horse.models";
import {OwnerService} from "../../owner/owner.service";

@Component({
    selector: 'app-horse-list',
    templateUrl: './horse-list.component.html',
    styleUrls: ['./horse-list.component.scss']
})
export class HorseListComponent implements OnInit {
    horses: HorseDto[] = [];
    owners: OwnerDto[] = [];
    currentHorse: CreateOrUpdateHorseDtoRq = { nickname: '', genderEnum: GenderEnum.MALE, age: 0, ownerId: 0 };

    constructor(private horseService: HorseService, private ownerService: OwnerService) {}

    ngOnInit() {
        this.loadHorses();
        this.loadOwners();
    }

    loadOwners() {
        this.ownerService.findAll().subscribe((data: OwnerDto[]) => {
            this.owners = data;
            console.log('Loaded owners:', this.owners); // Лог для проверки данных
        });
    }

    loadHorses() {
        this.horseService.findAll().subscribe((data) => {
            this.horses = data;
        });
    }

addHorse(e: any) {
    const newHorse: CreateOrUpdateHorseDtoRq = { ...e.data, ownerId: e.data.ownerId };
    if (!this.validateHorse(newHorse)) {
        console.error('Validation failed');
        e.cancel = true;
        return;
    }

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
    const updatedHorse: CreateOrUpdateHorseDtoRq = { ...e.oldData, ...e.newData, ownerId: e.newData.ownerId || e.oldData.ownerId };
    if (!this.validateHorse(updatedHorse)) {
        console.error('Validation failed');
        e.cancel = true;
        return;
    }

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

private validateHorse(horse: CreateOrUpdateHorseDtoRq): boolean {
    return !!horse.nickname && !!horse.genderEnum && horse.age != null && horse.ownerId != null;
}

    deleteHorse(e: any) {
        const horseId = e.data.id;
        e.promise = this.horseService.deleteById(horseId).toPromise().then(
            () => {
                this.loadHorses();
            },
            (error) => {
                console.error('Failed to delete horse', error);
                e.cancel = true;
            }
        );
    }
}
