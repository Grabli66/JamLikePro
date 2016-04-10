program SoundLibraryTest;

uses SysUtils, SoundLibraryUnit;

var sound: LongInt;

begin
  Init;
  sound:= AddSound('samples/kick.wav');
  PlaySound(sound);
  Sleep(500);
  PlaySound(sound);
  Sleep(500);
end.

