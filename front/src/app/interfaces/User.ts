export type UserType = 'CUSTOMER' | 'CUSTOMER_SUPPORT';

export interface User {
  id: number;
  firstname: string;
  lastname: string;
  email: string;
  password: string;
  birthdate: Date;
  type: UserType;
  createdAt: Date;
  updatedAt: Date;
}
