import { IUser } from './user.model';

export const sampleWithRequiredData: IUser = {
  id: 18411,
  login: 'FfqPy',
};

export const sampleWithPartialData: IUser = {
  id: 13160,
  login: 'k0@V\\2cG6lFe\\"gj8w\\~kc5qDo\\SZ\\kOr',
};

export const sampleWithFullData: IUser = {
  id: 12234,
  login: 's_d@1uN\\}CH0DxC\\&HpG\\!UG',
};
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
