import { Message } from './Message';
import { User } from './User';

export interface Answer {
  id: number;
  messageId: Message;
  user: User;
  message: string;
  createdAt: Date;
  updatedAt: Date;
}
