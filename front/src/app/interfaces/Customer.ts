import { User } from './User';

export interface Customer {
  customerId: number;
  address: string;
  userId?: number;
  customer?: User;
}
