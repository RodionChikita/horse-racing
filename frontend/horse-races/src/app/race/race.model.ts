export interface RaceDto {
    id: number;
    name: string;
    raceDate: string;
    raceTime: string;
    location: string;
}

export interface CreateOrUpdateRaceDtoRq {
    id: number;
    name: string;
    raceDate: string;
    raceTime: string;
    location: string;
}