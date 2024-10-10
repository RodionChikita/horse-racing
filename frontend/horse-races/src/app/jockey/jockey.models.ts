export interface CreateOrUpdateJockeyDtoRq {
    id?: number;
    name: string;
    address: string;
    age: number;
    rating: number;
}

export interface JockeyDto {
    id: number;
    name: string;
    address: string;
    age: number;
    rating: number;
}