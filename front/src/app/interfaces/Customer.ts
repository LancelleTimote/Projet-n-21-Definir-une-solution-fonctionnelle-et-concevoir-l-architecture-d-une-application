import { User } from './User';

export interface Customer {
  customerId: number;
  customer: User;
  address: string;
}
