import {OwnerDto} from "../owner/owner.models";

export interface HorseDto {
    id: number;
    nickname: string;
    genderEnum: GenderEnum;
    age: number;
    owner: OwnerDto;
}

export interface CreateOrUpdateHorseDtoRq {
    id?: number;
    nickname: string;
    genderEnum: GenderEnum;
    age: number;
    ownerId: number;
}

export enum GenderEnum {
    MALE = 'MALE',
    FEMALE = 'FEMALE'
}