import {Image, Text, View} from 'react-native';
import styled from 'styled-components/native';

export const Container = styled(View)`
  background-color: #fff;
  border-radius: 8px;
  overflow: hidden;
  height: 100px;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
`;

export const Border = styled(View)`
  background-color: #32a852;
  width: 8px;
  height: 100%;
`;

export const Icon = styled(Image)`
  margin-left: 12px;
`;

export const InfoContainer = styled(View)`
  flex: 1;
  padding-left: 24px;
`;

export const DeviceName = styled(Text)`
  color: #333;
  font-weight: bold;
  font-size: 24px;
`;

export const DeviceAddress = styled(Text)`
  color: #555;
  font-size: 18px;
`;
