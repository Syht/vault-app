import { IAuthority, NewAuthority } from './authority.model';

export const sampleWithRequiredData: IAuthority = {
  name: '6bd3b60e-ec0e-47ee-938c-8eab53bfef91',
};

export const sampleWithPartialData: IAuthority = {
  name: '1e36e7dd-a6a1-445b-bb7b-14afd073ddfb',
};

export const sampleWithFullData: IAuthority = {
  name: 'b8810203-a20b-44f0-bea8-b4ac7014e4d5',
};

export const sampleWithNewData: NewAuthority = {
  name: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
