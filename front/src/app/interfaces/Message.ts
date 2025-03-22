import { Conversation } from './Conversation';
import { User } from './User';

export interface Message {
  id: number;
  conversation: Conversation;
  sender: User;
  message: string;
  createdAt: Date;
  updatedAt: Date;
}
