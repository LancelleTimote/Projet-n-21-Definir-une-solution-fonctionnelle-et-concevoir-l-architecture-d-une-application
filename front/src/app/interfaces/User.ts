import { Customer } from './Customer';
import { CustomerSupport } from './CustomerSupport';

export interface User {
  id: number;
  firstname: string;
  lastname: string;
  email: string;
  password: string;
  birthdate: Date;
  userType: string;
  createdAt?: Date;
  updatedAt?: Date;
  customer?: Customer | undefined;
  customer_support?: CustomerSupport;
}
