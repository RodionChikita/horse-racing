export interface CreateOrUpdateOwnerDtoRq {
    id?: number;
    name: string;
    address: string;
    phoneNumber: string;
}

export interface OwnerDto {
    id: number;
    name: string;
    address: string;
    phoneNumber: string;
}