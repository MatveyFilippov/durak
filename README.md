# DURAK
### Доаботка приложения до начального состояния игры (роздано по 6 карт каждому игроку и определено кто ходит первым)
***
***
***
# Что было сделано:
- Во время вызова окна GameWindow закрывается окно EnteraceWindow
- Добавлены 2 кнопки "add card", каждая привязана к игроку и добавляет пользовотелю столько карт, сколько ему требуется (не более 6). Когда кнопка не нужна она не работает (Disable)
- По середине реализована коллода из которой берутся карты (рубашка вверх и сколько карт осталось)
- Мы увидели, что у вас поле делится на атаку и защиту, поэтому добавили подпись кто из игроков атакует (рандом), cоотвественно ему присвоено атакующее поле
- Простейшая доккументация (комментарии к функциям)
# Что не сделано:
- Игральная карта не имеет фотографии (строчка масти и номинала)
- Под коллодой открытая ведущая карта (решение о козыре)
