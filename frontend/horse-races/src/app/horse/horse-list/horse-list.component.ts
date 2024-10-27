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

    loadHorses() {
        this.horseService.findAll().subscribe((data) => {
            this.horses = data;
        });
    }

    loadOwners() {
        this.ownerService.findAll().subscribe((data) => {
            this.owners = data;
            console.log('Owners Loaded: ', this.owners);
        });
    }

    addHorse(e: any) {
        const newHorse: CreateOrUpdateHorseDtoRq = { ...e.data, ownerId: e.data.ownerId };
        console.log('Adding horse with data:', newHorse);
        if (newHorse.ownerId == null) {
            console.error('Owner ID is undefined, cannot add horse');
            e.cancel = true;
            return;
        }
        e.promise = this.horseService.insert(newHorse).toPromise().then(
            () => {
                this.loadHorses();
            },
            (error) => {
                console.error('Failed to add horse', error);
                e.cancel = true;
            }
        );
    }

    updateHorse(e: any) {
        const updatedHorse: CreateOrUpdateHorseDtoRq = { ...e.oldData, ...e.newData, ownerId: e.newData.ownerId || e.oldData.ownerId };
        console.log('Updating horse with data:', updatedHorse);
        if (updatedHorse.ownerId == null) {
            console.error('Owner ID is undefined, cannot update horse');
            e.cancel = true;
            return;
        }
        e.promise = this.horseService.update(updatedHorse).toPromise().then(
            () => {
                this.loadHorses();
            },
            (error) => {
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
            (error) => {
                console.error('Failed to delete horse', error);
                e.cancel = true;
            }
        );
    }
}
