CC := g++-4.8
FLAGS := -std=c++11 -gdwarf-2
BIN_DIR := bin
BUILD_DIR := build
INC_DIR := include
INCLUDE_DIR := -I./$(INC_DIR)
SRC_DIR := src
OBJS := $(BUILD_DIR)/AgendaService.o $(BUILD_DIR)/AgendaUI.o $(BUILD_DIR)/Date.o $(BUILD_DIR)/Meeting.o $(BUILD_DIR)/Storage.o $(BUILD_DIR)/User.o $(BUILD_DIR)/Agenda.o

$(BIN_DIR)/Agenda : $(OBJS)
	@mkdir -p $(BIN_DIR)
	$(CC) $(FLAGS) $(INCLUDE_DIR) $(OBJS) -o $@

$(BUILD_DIR)/Agenda.o : $(SRC_DIR)/Agenda.cpp $(BUILD_DIR)/AgendaUI.o
	@mkdir -p $(BUILD_DIR)
	$(CC) $(FLAGS) $(INCLUDE_DIR) -c $(SRC_DIR)/Agenda.cpp -o $@

$(BUILD_DIR)/AgendaService.o : $(SRC_DIR)/AgendaService.cpp $(BUILD_DIR)/Storage.o
	@mkdir -p $(BUILD_DIR)
	$(CC) $(FLAGS) $(INCLUDE_DIR) -c $(SRC_DIR)/AgendaService.cpp -o $@

$(BUILD_DIR)/AgendaUI.o : $(SRC_DIR)/AgendaUI.cpp $(BUILD_DIR)/AgendaService.o
	@mkdir -p $(BUILD_DIR)
	$(CC) $(FLAGS) $(INCLUDE_DIR) -c $(SRC_DIR)/AgendaUI.cpp -o $@

$(BUILD_DIR)/Meeting.o : $(BUILD_DIR)/Date.o $(SRC_DIR)/Meeting.cpp
	@mkdir -p $(BUILD_DIR)
	$(CC) $(FLAGS) $(INCLUDE_DIR) -c $(SRC_DIR)/Meeting.cpp -o $@

$(BUILD_DIR)/Storage.o : $(BUILD_DIR)/User.o $(BUILD_DIR)/Meeting.o $(SRC_DIR)/Storage.cpp
	@mkdir -p $(BUILD_DIR)
	$(CC) $(FLAGS) $(INCLUDE_DIR) -c $(SRC_DIR)/Storage.cpp -o $@

$(BUILD_DIR)/User.o : $(SRC_DIR)/User.cpp 
	@mkdir -p $(BUILD_DIR)
	$(CC) $(FLAGS) $(INCLUDE_DIR) -c $^ -o $@

$(BUILD_DIR)/Date.o : $(SRC_DIR)/Date.cpp
	@mkdir -p $(BUILD_DIR)
	$(CC) $(FLAGS) $(INCLUDE_DIR) -c $^ -o $@

clean:
	rm -rf $(BUILD_DIR)
	rm -rf $(BIN_DIR)
