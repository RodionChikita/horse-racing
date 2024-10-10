import {JockeyDto} from "../jockey/jockey.models";
import {HorseDto} from "../horse/horse.models";

export interface CreateOrUpdateRaceResultDtoRq {
    id?: number;  // Optional for creation
    place: string;
    horseTime: string; // Adjust this type as needed
    raceId: number;
    jockeyId: number;
    horseId: number;
}

export interface RaceResultDto {
    id?: number;
    place: string;
    horseTime: string;
    raceId: number;
    jockey: JockeyDto; // Include DTOs for jockey and horse if needed
    horse: HorseDto;
}