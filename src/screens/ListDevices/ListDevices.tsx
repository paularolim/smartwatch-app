import React from 'react';
import {
  NativeModules,
  Text,
  TouchableOpacity,
  TouchableOpacityProps,
  View,
} from 'react-native';

const Button = ({children, onPress}: TouchableOpacityProps) => {
  return (
    <TouchableOpacity
      onPress={onPress}
      style={{backgroundColor: 'red', padding: 10, marginVertical: 5}}>
      <Text>{children}</Text>
    </TouchableOpacity>
  );
};

export const ListDevices = () => {
  return (
    <View style={{padding: 24}}>
      <Text>List</Text>

      <Button onPress={() => {}}>Request</Button>

      <Button
        onPress={() => {
          NativeModules.BluetoothConnection.isBluetoothEnabled();
        }}>
        isBluetoothEnabled
      </Button>

      <Button
        onPress={() => {
          NativeModules.BluetoothConnection.listPairedDevices();
        }}>
        listPairedDevices
      </Button>

      <Button
        onPress={() => {
          NativeModules.BluetoothConnection.hasBluetoothSupport();
        }}>
        hasBluetoothSupport
      </Button>

      <Button
        onPress={() => {
          NativeModules.BluetoothConnection.listDiscoveryDevices();
        }}>
        listDiscoveryDevices
      </Button>
    </View>
  );
};
