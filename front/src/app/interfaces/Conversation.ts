import { Customer } from './Customer';
import { CustomerSupport } from './CustomerSupport';

export interface Conversation {
  id: number;
  customer: Customer;
  customerSupport: CustomerSupport;
  createdAt: Date;
  updatedAt: Date;
}
