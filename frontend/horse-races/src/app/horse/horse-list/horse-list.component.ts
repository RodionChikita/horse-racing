import { Component, OnInit } from '@angular/core';
import { HorseService } from '../horse.service';
import {OwnerDto} from "../../owner/owner.models";
import {CreateOrUpdateHorseDtoRq, HorseDto} from "../horse.models";
import {OwnerService} from "../../owner/owner.service";

@Component({
    selector: 'app-horse-list',
    templateUrl: './horse-list.component.html',
    styleUrls: ['./horse-list.component.scss']
})
export class HorseListComponent implements OnInit {
    horses: HorseDto[] = [];
    owners: OwnerDto[] = []; // Store available owners for selection

    constructor(private horseService: HorseService, private ownerService: OwnerService) {}

    ngOnInit() {
        this.loadHorses();
        this.loadOwners();
    }

    // Load horses from the backend
    loadHorses() {
        this.horseService.findAll().subscribe((data) => {
            this.horses = data;
        });
    }

    // Load owners from the backend for selection
    loadOwners() {
        this.ownerService.findAll().subscribe((data) => {
            this.owners = data;
        });
    }

    // Add a new horse
    addHorse(e: any) {
        const newHorse: CreateOrUpdateHorseDtoRq = e.data;
        console.log('Adding horse with data:', newHorse);
        e.promise = this.horseService.insert(newHorse).toPromise().then(
            () => {
                this.loadHorses(); // Reload data once insertion is successful
            },
            (error) => {
                console.error('Failed to add horse', error);
                e.cancel = true;
            }
        );
    }

    // Update an existing horse
    updateHorse(e: any) {
        const updatedHorse: CreateOrUpdateHorseDtoRq = { ...e.oldData, ...e.newData };
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

    // Delete a horse by ID
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
