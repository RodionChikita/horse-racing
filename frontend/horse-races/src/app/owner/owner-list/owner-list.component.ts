import { Component, OnInit } from '@angular/core';
import { OwnerService } from '../owner.service';
import { OwnerDto, CreateOrUpdateOwnerDtoRq } from '../owner.models';

@Component({
    selector: 'app-owner-list',
    templateUrl: './owner-list.component.html',
    styleUrls: ['./owner-list.component.scss']
})
export class OwnerListComponent implements OnInit {
    owners: OwnerDto[] = [];

    constructor(private ownerService: OwnerService) {}

    ngOnInit() {
        this.loadOwners();
    }

    loadOwners() {
        this.ownerService.findAll().subscribe(data => {
            this.owners = data;
        });
    }

    // Add a new owner
    addOwner(e: any) {
        const newOwner: CreateOrUpdateOwnerDtoRq = e.data;
        this.ownerService.insert(newOwner).subscribe(() => {
            this.loadOwners();
        });
    }

    // Update an existing owner
    updateOwner(e: any) {
        const updatedOwner: CreateOrUpdateOwnerDtoRq = { ...e.oldData, ...e.newData };
        this.ownerService.update(updatedOwner).subscribe(() => {
            this.loadOwners();
        });
    }

    // Delete an owner by ID
    deleteOwner(e: any) {
        const ownerId = e.data.id;
        this.ownerService.deleteById(ownerId).subscribe(() => {
            this.loadOwners();
        });
    }
}